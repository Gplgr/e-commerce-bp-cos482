/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ProdutoComponent from '@/entities/produto/produto.vue';
import ProdutoClass from '@/entities/produto/produto.component';
import ProdutoService from '@/entities/produto/produto.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Produto Management Component', () => {
    let wrapper: Wrapper<ProdutoClass>;
    let comp: ProdutoClass;
    let produtoServiceStub: SinonStubbedInstance<ProdutoService>;

    beforeEach(() => {
      produtoServiceStub = sinon.createStubInstance<ProdutoService>(ProdutoService);
      produtoServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<ProdutoClass>(ProdutoComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          produtoService: () => produtoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      produtoServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllProdutos();
      await comp.$nextTick();

      // THEN
      expect(produtoServiceStub.retrieve.called).toBeTruthy();
      expect(comp.produtos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      produtoServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeProduto();
      await comp.$nextTick();

      // THEN
      expect(produtoServiceStub.delete.called).toBeTruthy();
      expect(produtoServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});

/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ProdutoDetailComponent from '@/entities/produto/produto-details.vue';
import ProdutoClass from '@/entities/produto/produto-details.component';
import ProdutoService from '@/entities/produto/produto.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Produto Management Detail Component', () => {
    let wrapper: Wrapper<ProdutoClass>;
    let comp: ProdutoClass;
    let produtoServiceStub: SinonStubbedInstance<ProdutoService>;

    beforeEach(() => {
      produtoServiceStub = sinon.createStubInstance<ProdutoService>(ProdutoService);

      wrapper = shallowMount<ProdutoClass>(ProdutoDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { produtoService: () => produtoServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundProduto = { id: 123 };
        produtoServiceStub.find.resolves(foundProduto);

        // WHEN
        comp.retrieveProduto(123);
        await comp.$nextTick();

        // THEN
        expect(comp.produto).toBe(foundProduto);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundProduto = { id: 123 };
        produtoServiceStub.find.resolves(foundProduto);

        // WHEN
        comp.beforeRouteEnter({ params: { produtoId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.produto).toBe(foundProduto);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});

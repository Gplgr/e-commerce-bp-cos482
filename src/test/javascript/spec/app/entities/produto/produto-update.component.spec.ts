/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import * as config from '@/shared/config/config';
import ProdutoUpdateComponent from '@/entities/produto/produto-update.vue';
import ProdutoClass from '@/entities/produto/produto-update.component';
import ProdutoService from '@/entities/produto/produto.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Produto Management Update Component', () => {
    let wrapper: Wrapper<ProdutoClass>;
    let comp: ProdutoClass;
    let produtoServiceStub: SinonStubbedInstance<ProdutoService>;

    beforeEach(() => {
      produtoServiceStub = sinon.createStubInstance<ProdutoService>(ProdutoService);

      wrapper = shallowMount<ProdutoClass>(ProdutoUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          produtoService: () => produtoServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.produto = entity;
        produtoServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(produtoServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.produto = entity;
        produtoServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(produtoServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundProduto = { id: 123 };
        produtoServiceStub.find.resolves(foundProduto);
        produtoServiceStub.retrieve.resolves([foundProduto]);

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

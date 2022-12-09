/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import EcommerceDetailComponent from '@/entities/ecommerce/ecommerce-details.vue';
import EcommerceClass from '@/entities/ecommerce/ecommerce-details.component';
import EcommerceService from '@/entities/ecommerce/ecommerce.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Ecommerce Management Detail Component', () => {
    let wrapper: Wrapper<EcommerceClass>;
    let comp: EcommerceClass;
    let ecommerceServiceStub: SinonStubbedInstance<EcommerceService>;

    beforeEach(() => {
      ecommerceServiceStub = sinon.createStubInstance<EcommerceService>(EcommerceService);

      wrapper = shallowMount<EcommerceClass>(EcommerceDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { ecommerceService: () => ecommerceServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundEcommerce = { id: 123 };
        ecommerceServiceStub.find.resolves(foundEcommerce);

        // WHEN
        comp.retrieveEcommerce(123);
        await comp.$nextTick();

        // THEN
        expect(comp.ecommerce).toBe(foundEcommerce);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundEcommerce = { id: 123 };
        ecommerceServiceStub.find.resolves(foundEcommerce);

        // WHEN
        comp.beforeRouteEnter({ params: { ecommerceId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.ecommerce).toBe(foundEcommerce);
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

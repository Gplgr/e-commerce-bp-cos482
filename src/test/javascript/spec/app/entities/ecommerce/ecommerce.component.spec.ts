/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import EcommerceComponent from '@/entities/ecommerce/ecommerce.vue';
import EcommerceClass from '@/entities/ecommerce/ecommerce.component';
import EcommerceService from '@/entities/ecommerce/ecommerce.service';

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
  describe('Ecommerce Management Component', () => {
    let wrapper: Wrapper<EcommerceClass>;
    let comp: EcommerceClass;
    let ecommerceServiceStub: SinonStubbedInstance<EcommerceService>;

    beforeEach(() => {
      ecommerceServiceStub = sinon.createStubInstance<EcommerceService>(EcommerceService);
      ecommerceServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<EcommerceClass>(EcommerceComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          ecommerceService: () => ecommerceServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      ecommerceServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllEcommerces();
      await comp.$nextTick();

      // THEN
      expect(ecommerceServiceStub.retrieve.called).toBeTruthy();
      expect(comp.ecommerces[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

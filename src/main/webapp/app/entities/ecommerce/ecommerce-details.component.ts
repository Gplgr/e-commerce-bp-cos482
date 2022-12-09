import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEcommerce } from '@/shared/model/ecommerce.model';
import EcommerceService from './ecommerce.service';

@Component
export default class EcommerceDetails extends Vue {
  @Inject('ecommerceService') private ecommerceService: () => EcommerceService;
  public ecommerce: IEcommerce = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ecommerceId) {
        vm.retrieveEcommerce(to.params.ecommerceId);
      }
    });
  }

  public retrieveEcommerce(ecommerceId) {
    this.ecommerceService()
      .find(ecommerceId)
      .then(res => {
        this.ecommerce = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEcommerceProcess } from '@/shared/model/ecommerce-process.model';
import EcommerceProcessService from './ecommerce-process.service';

@Component
export default class EcommerceProcessDetailsComponent extends Vue {
  @Inject('ecommerceProcessService') private ecommerceProcessService: () => EcommerceProcessService;
  public ecommerceProcess: IEcommerceProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveEcommerceProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveEcommerceProcess(ecommerceProcessId) {
    this.isFetching = true;
    this.ecommerceProcessService()
      .find(ecommerceProcessId)
      .then(
        res => {
          this.ecommerceProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}

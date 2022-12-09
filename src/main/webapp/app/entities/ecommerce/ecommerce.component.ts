import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IEcommerce } from '@/shared/model/ecommerce.model';

import EcommerceService from './ecommerce.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Ecommerce extends Vue {
  @Inject('ecommerceService') private ecommerceService: () => EcommerceService;
  private removeId: number = null;

  public ecommerces: IEcommerce[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllEcommerces();
  }

  public clear(): void {
    this.retrieveAllEcommerces();
  }

  public retrieveAllEcommerces(): void {
    this.isFetching = true;

    this.ecommerceService()
      .retrieve()
      .then(
        res => {
          this.ecommerces = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

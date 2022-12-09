import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IProduto } from '@/shared/model/produto.model';

import ProdutoService from './produto.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Produto extends Vue {
  @Inject('produtoService') private produtoService: () => ProdutoService;
  private removeId: number = null;

  public produtos: IProduto[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllProdutos();
  }

  public clear(): void {
    this.retrieveAllProdutos();
  }

  public retrieveAllProdutos(): void {
    this.isFetching = true;

    this.produtoService()
      .retrieve()
      .then(
        res => {
          this.produtos = res.data;
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

  public prepareRemove(instance: IProduto): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeProduto(): void {
    this.produtoService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('ecommerceApp.produto.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllProdutos();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}

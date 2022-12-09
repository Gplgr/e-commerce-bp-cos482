import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProduto, Produto } from '@/shared/model/produto.model';
import ProdutoService from './produto.service';

const validations: any = {
  produto: {
    name: {},
    price: {},
  },
};

@Component({
  validations,
})
export default class ProdutoUpdate extends Vue {
  @Inject('produtoService') private produtoService: () => ProdutoService;
  public produto: IProduto = new Produto();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.produtoId) {
        vm.retrieveProduto(to.params.produtoId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.produto.id) {
      this.produtoService()
        .update(this.produto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ecommerceApp.produto.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.produtoService()
        .create(this.produto)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('ecommerceApp.produto.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveProduto(produtoId): void {
    this.produtoService()
      .find(produtoId)
      .then(res => {
        this.produto = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import { IProduto } from '@/shared/model/produto.model';
import ProdutoService from './produto.service';

@Component
export default class ProdutoDetails extends Vue {
  @Inject('produtoService') private produtoService: () => ProdutoService;
  public produto: IProduto = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.produtoId) {
        vm.retrieveProduto(to.params.produtoId);
      }
    });
  }

  public retrieveProduto(produtoId) {
    this.produtoService()
      .find(produtoId)
      .then(res => {
        this.produto = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

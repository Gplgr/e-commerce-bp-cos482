import { Component, Vue, Inject } from 'vue-property-decorator';

import ProdutoService from '@/entities/produto/produto.service';
import { IProduto } from '@/shared/model/produto.model';

import TaskSelecaoService from './task-selecao.service';
import { TaskSelecaoContext } from './task-selecao.model';

const validations: any = {
  taskContext: {
    ecommerceProcess: {
      ecommerce: {
        userID: {},
        productQuantity: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskSelecaoExecuteComponent extends Vue {
  private taskSelecaoService: TaskSelecaoService = new TaskSelecaoService();
  private taskContext: TaskSelecaoContext = {};

  @Inject('produtoService') private produtoService: () => ProdutoService;

  public produtos: IProduto[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskSelecaoService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskSelecaoService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.produtoService()
      .retrieve()
      .then(res => {
        this.produtos = res.data;
      });
  }
}

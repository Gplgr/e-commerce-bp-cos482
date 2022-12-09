import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskConfirmacaoService from './task-confirmacao.service';
import { TaskConfirmacaoContext } from './task-confirmacao.model';

const validations: any = {
  taskContext: {
    ecommerceProcess: {
      ecommerce: {
        userID: {},
        productQuantity: {},
        confirmation: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskConfirmacaoExecuteComponent extends Vue {
  private taskConfirmacaoService: TaskConfirmacaoService = new TaskConfirmacaoService();
  private taskContext: TaskConfirmacaoContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskConfirmacaoService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskConfirmacaoService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}

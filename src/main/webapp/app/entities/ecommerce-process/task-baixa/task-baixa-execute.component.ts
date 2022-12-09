import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBaixaService from './task-baixa.service';
import { TaskBaixaContext } from './task-baixa.model';

const validations: any = {
  taskContext: {
    ecommerceProcess: {
      ecommerce: {
        userID: {},
        productQuantity: {},
        productsAvailable: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskBaixaExecuteComponent extends Vue {
  private taskBaixaService: TaskBaixaService = new TaskBaixaService();
  private taskContext: TaskBaixaContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskBaixaService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskBaixaService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}

import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskConfirmacaoService from './task-confirmacao.service';
import { TaskConfirmacaoContext } from './task-confirmacao.model';

@Component
export default class TaskConfirmacaoDetailsComponent extends Vue {
  private taskConfirmacaoService: TaskConfirmacaoService = new TaskConfirmacaoService();
  private taskContext: TaskConfirmacaoContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskConfirmacaoService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

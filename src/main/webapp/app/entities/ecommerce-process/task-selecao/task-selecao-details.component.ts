import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSelecaoService from './task-selecao.service';
import { TaskSelecaoContext } from './task-selecao.model';

@Component
export default class TaskSelecaoDetailsComponent extends Vue {
  private taskSelecaoService: TaskSelecaoService = new TaskSelecaoService();
  private taskContext: TaskSelecaoContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskSelecaoService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

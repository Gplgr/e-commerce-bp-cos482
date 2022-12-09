import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBaixaService from './task-baixa.service';
import { TaskBaixaContext } from './task-baixa.model';

@Component
export default class TaskBaixaDetailsComponent extends Vue {
  private taskBaixaService: TaskBaixaService = new TaskBaixaService();
  private taskContext: TaskBaixaContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskBaixaService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}

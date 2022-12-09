import { Component, Vue, Inject } from 'vue-property-decorator';
import { IEcommerceProcess } from '@/shared/model/ecommerce-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import EcommerceProcessService from './ecommerce-process.service';

@Component
export default class EcommerceProcessListComponent extends Vue {
  @Inject('ecommerceProcessService') private ecommerceProcessService: () => EcommerceProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'EcommerceProcess';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public ecommerceProcessList: IEcommerceProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.ecommerceProcessService()
      .retrieve()
      .then(
        res => {
          this.ecommerceProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}

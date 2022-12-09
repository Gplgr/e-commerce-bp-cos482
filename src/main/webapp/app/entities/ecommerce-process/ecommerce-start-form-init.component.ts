import { Component, Vue, Inject } from 'vue-property-decorator';

import { IEcommerceProcess, EcommerceProcess } from '@/shared/model/ecommerce-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Ecommerce } from '@/shared/model/ecommerce.model';
import EcommerceProcessService from './ecommerce-process.service';

const validations: any = {
  ecommerceProcess: {
    ecommerce: {
      userID: {},
    },
  },
};

@Component({
  validations,
})
export default class EcommerceStartFormInitComponent extends Vue {
  @Inject('ecommerceProcessService') private ecommerceProcessService: () => EcommerceProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'EcommerceProcess';
  public ecommerceProcess: IEcommerceProcess = new EcommerceProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initEcommerceStartForm();
      vm.initRelationships();
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

    this.ecommerceProcessService()
      .create(this.ecommerceProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('ecommerceApp.ecommerceStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initEcommerceStartForm(): void {
    this.ecommerceProcess.ecommerce = new Ecommerce();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.ecommerceProcess.processInstance = new ProcessInstance();
      this.ecommerceProcess.processInstance.processDefinition = res;
    });
  }
}

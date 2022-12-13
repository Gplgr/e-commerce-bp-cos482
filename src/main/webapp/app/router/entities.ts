import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Ecommerce = () => import('@/entities/ecommerce/ecommerce.vue');
// prettier-ignore
const EcommerceDetails = () => import('@/entities/ecommerce/ecommerce-details.vue');
// prettier-ignore
const EcommerceStartFormInit = () => import('@/entities/ecommerce-process/ecommerce-start-form-init.vue');
// prettier-ignore
const EcommerceProcess_TaskBaixaDetails = () => import('@/entities/ecommerce-process/task-baixa/task-baixa-details.vue');
// prettier-ignore
const EcommerceProcess_TaskBaixaExecute = () => import('@/entities/ecommerce-process/task-baixa/task-baixa-execute.vue');
// prettier-ignore
const EcommerceProcess_TaskConfirmacaoDetails = () => import('@/entities/ecommerce-process/task-confirmacao/task-confirmacao-details.vue');
// prettier-ignore
const EcommerceProcess_TaskConfirmacaoExecute = () => import('@/entities/ecommerce-process/task-confirmacao/task-confirmacao-execute.vue');
// prettier-ignore
const EcommerceProcess_TaskSelecaoDetails = () => import('@/entities/ecommerce-process/task-selecao/task-selecao-details.vue');
// prettier-ignore
const EcommerceProcess_TaskSelecaoExecute = () => import('@/entities/ecommerce-process/task-selecao/task-selecao-execute.vue');
// prettier-ignore
const EcommerceProcessDetails = () => import('@/entities/ecommerce-process/ecommerce-process-details.vue');
// prettier-ignore
const EcommerceProcessList = () => import('@/entities/ecommerce-process/ecommerce-process-list.vue');
// prettier-ignore
const Produto = () => import('@/entities/produto/produto.vue');
// prettier-ignore
const ProdutoUpdate = () => import('@/entities/produto/produto-update.vue');
// prettier-ignore
const ProdutoDetails = () => import('@/entities/produto/produto-details.vue');
// prettier-ignore
const Ecommerce = () => import('@/entities/ecommerce/ecommerce.vue');
// prettier-ignore
const EcommerceDetails = () => import('@/entities/ecommerce/ecommerce-details.vue');
// prettier-ignore
const EcommerceStartFormInit = () => import('@/entities/ecommerce-process/ecommerce-start-form-init.vue');
// prettier-ignore
const Produto = () => import('@/entities/produto/produto.vue');
// prettier-ignore
const ProdutoUpdate = () => import('@/entities/produto/produto-update.vue');
// prettier-ignore
const ProdutoDetails = () => import('@/entities/produto/produto-details.vue');
// prettier-ignore
const EcommerceProcess_TaskBaixaDetails = () => import('@/entities/ecommerce-process/task-baixa/task-baixa-details.vue');
// prettier-ignore
const EcommerceProcess_TaskBaixaExecute = () => import('@/entities/ecommerce-process/task-baixa/task-baixa-execute.vue');
// prettier-ignore
const EcommerceProcess_TaskConfirmacaoDetails = () => import('@/entities/ecommerce-process/task-confirmacao/task-confirmacao-details.vue');
// prettier-ignore
const EcommerceProcess_TaskConfirmacaoExecute = () => import('@/entities/ecommerce-process/task-confirmacao/task-confirmacao-execute.vue');
// prettier-ignore
const EcommerceProcess_TaskSelecaoDetails = () => import('@/entities/ecommerce-process/task-selecao/task-selecao-details.vue');
// prettier-ignore
const EcommerceProcess_TaskSelecaoExecute = () => import('@/entities/ecommerce-process/task-selecao/task-selecao-execute.vue');
// prettier-ignore
const EcommerceProcessDetails = () => import('@/entities/ecommerce-process/ecommerce-process-details.vue');
// prettier-ignore
const EcommerceProcessList = () => import('@/entities/ecommerce-process/ecommerce-process-list.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/ecommerce',
    name: 'Ecommerce',
    component: Ecommerce,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/ecommerce/:ecommerceId/view',
    name: 'EcommerceView',
    component: EcommerceDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/init',
    name: 'EcommerceStartFormInit',
    component: EcommerceStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskBaixa/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskBaixaDetails',
    component: EcommerceProcess_TaskBaixaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskBaixa/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskBaixaExecute',
    component: EcommerceProcess_TaskBaixaExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskConfirmacao/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskConfirmacaoDetails',
    component: EcommerceProcess_TaskConfirmacaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskConfirmacao/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskConfirmacaoExecute',
    component: EcommerceProcess_TaskConfirmacaoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskSelecao/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskSelecaoDetails',
    component: EcommerceProcess_TaskSelecaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskSelecao/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskSelecaoExecute',
    component: EcommerceProcess_TaskSelecaoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/instance/:processInstanceId/view',
    name: 'EcommerceProcessView',
    component: EcommerceProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/instances',
    name: 'EcommerceProcessList',
    component: EcommerceProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/produto',
    name: 'Produto',
    component: Produto,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/produto/new',
    name: 'ProdutoCreate',
    component: ProdutoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/produto/:produtoId/edit',
    name: 'ProdutoEdit',
    component: ProdutoUpdate,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/produto/:produtoId/view',
    name: 'ProdutoView',
    component: ProdutoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/init',
    name: 'EcommerceStartFormInit',
    component: EcommerceStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskBaixa/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskBaixaDetails',
    component: EcommerceProcess_TaskBaixaDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskBaixa/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskBaixaExecute',
    component: EcommerceProcess_TaskBaixaExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskConfirmacao/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskConfirmacaoDetails',
    component: EcommerceProcess_TaskConfirmacaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskConfirmacao/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskConfirmacaoExecute',
    component: EcommerceProcess_TaskConfirmacaoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskSelecao/:taskInstanceId/view',
    name: 'EcommerceProcess_TaskSelecaoDetails',
    component: EcommerceProcess_TaskSelecaoDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/task/TaskSelecao/:taskInstanceId/execute',
    name: 'EcommerceProcess_TaskSelecaoExecute',
    component: EcommerceProcess_TaskSelecaoExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/instance/:processInstanceId/view',
    name: 'EcommerceProcessView',
    component: EcommerceProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/EcommerceProcess/instances',
    name: 'EcommerceProcessList',
    component: EcommerceProcessList,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];

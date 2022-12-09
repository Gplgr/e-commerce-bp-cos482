<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('ecommerceApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskSelecao.userID')">userID</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="userID"
                id="ecommerce-userID"
                data-cy="userID"
                v-model="taskContext.ecommerceProcess.ecommerce.userID"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskSelecao.productQuantity')">productQuantity</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="productQuantity"
                id="ecommerce-productQuantity"
                data-cy="productQuantity"
                v-model="taskContext.ecommerceProcess.ecommerce.productQuantity"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskSelecao.produto')" for="task-selecao-produto">Produto</label>
              <input
                v-if="taskContext.ecommerceProcess.ecommerce.produto"
                readonly
                type="text"
                class="form-control"
                name="produto"
                id="ecommerce-produto"
                data-cy="produto"
                :value="taskContext.ecommerceProcess.ecommerce.produto.name"
              />
              <input v-else readonly type="text" class="form-control" name="produto" id="ecommerce-produto" data-cy="produto" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/EcommerceProcess/task/TaskSelecao/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-selecao-details.component.ts"></script>

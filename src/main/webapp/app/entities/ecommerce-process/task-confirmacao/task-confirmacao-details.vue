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
              <label class="form-control-label" v-text="$t('ecommerceApp.taskConfirmacao.userID')">userID</label>
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
              <label class="form-control-label" v-text="$t('ecommerceApp.taskConfirmacao.productQuantity')">productQuantity</label>
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
              <label class="form-control-label" v-text="$t('ecommerceApp.taskConfirmacao.confirmation')">confirmation</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="confirmation"
                id="ecommerce-confirmation"
                data-cy="confirmation"
                v-model="taskContext.ecommerceProcess.ecommerce.confirmation"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/EcommerceProcess/task/TaskConfirmacao/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-confirmacao-details.component.ts"></script>

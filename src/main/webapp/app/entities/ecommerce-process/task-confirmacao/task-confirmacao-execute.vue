<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('ecommerceApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskConfirmacao.userID')" for="task-confirmacao-userID"
                >User ID</label
              >
              <input
                type="text"
                class="form-control"
                name="userID"
                id="task-confirmacao-userID"
                readonly
                data-cy="userID"
                :class="{
                  valid: !$v.taskContext.ecommerceProcess.ecommerce.userID.$invalid,
                  invalid: $v.taskContext.ecommerceProcess.ecommerce.userID.$invalid,
                }"
                v-model="$v.taskContext.ecommerceProcess.ecommerce.userID.$model"
              />
            </div>
            <div class="form-group">
              <label
                class="form-control-label"
                v-text="$t('ecommerceApp.taskConfirmacao.productQuantity')"
                for="task-confirmacao-productQuantity"
                >Product Quantity</label
              >
              <input
                type="number"
                class="form-control"
                name="productQuantity"
                id="task-confirmacao-productQuantity"
                readonly
                data-cy="productQuantity"
                :class="{
                  valid: !$v.taskContext.ecommerceProcess.ecommerce.productQuantity.$invalid,
                  invalid: $v.taskContext.ecommerceProcess.ecommerce.productQuantity.$invalid,
                }"
                v-model.number="$v.taskContext.ecommerceProcess.ecommerce.productQuantity.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskConfirmacao.confirmation')" for="task-confirmacao-confirmation"
                >Confirmation</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="confirmation"
                id="task-confirmacao-confirmation"
                data-cy="confirmation"
                :class="{
                  valid: !$v.taskContext.ecommerceProcess.ecommerce.confirmation.$invalid,
                  invalid: $v.taskContext.ecommerceProcess.ecommerce.confirmation.$invalid,
                }"
                v-model="$v.taskContext.ecommerceProcess.ecommerce.confirmation.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-confirmacao-execute.component.ts"></script>

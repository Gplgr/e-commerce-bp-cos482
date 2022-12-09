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
              <label class="form-control-label" v-text="$t('ecommerceApp.taskBaixa.userID')" for="task-baixa-userID">User ID</label>
              <input
                type="text"
                class="form-control"
                name="userID"
                id="task-baixa-userID"
                data-cy="userID"
                :class="{
                  valid: !$v.taskContext.ecommerceProcess.ecommerce.userID.$invalid,
                  invalid: $v.taskContext.ecommerceProcess.ecommerce.userID.$invalid,
                }"
                v-model="$v.taskContext.ecommerceProcess.ecommerce.userID.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ecommerceApp.taskBaixa.productQuantity')" for="task-baixa-productQuantity"
                >Product Quantity</label
              >
              <input
                type="number"
                class="form-control"
                name="productQuantity"
                id="task-baixa-productQuantity"
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
              <label class="form-control-label" v-text="$t('ecommerceApp.taskBaixa.productsAvailable')" for="task-baixa-productsAvailable"
                >Products Available</label
              >
              <input
                type="number"
                class="form-control"
                name="productsAvailable"
                id="task-baixa-productsAvailable"
                data-cy="productsAvailable"
                :class="{
                  valid: !$v.taskContext.ecommerceProcess.ecommerce.productsAvailable.$invalid,
                  invalid: $v.taskContext.ecommerceProcess.ecommerce.productsAvailable.$invalid,
                }"
                v-model.number="$v.taskContext.ecommerceProcess.ecommerce.productsAvailable.$model"
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

<script lang="ts" src="./task-baixa-execute.component.ts"></script>

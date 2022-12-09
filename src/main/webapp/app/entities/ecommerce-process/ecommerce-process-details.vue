<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="ecommerceProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="ecommerceProcessDetailsHeading">
          <span v-text="$t('ecommerceApp.ecommerceProcess.detail.title')">EcommerceProcess</span> {{ ecommerceProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="ecommerceProcess.processInstance">
          <template v-slot:title>
            <span v-text="$t('ecommerceApp.ecommerceProcess.detail.title')">EcommerceProcess</span> #{{ ecommerceProcess.id }}
          </template>
          <template v-slot:details>
            <div class="card" v-if="ecommerceProcess.ecommerce">
              <h5 class="card-header">
                <span class="title"> Ecommerce #{{ ecommerceProcess.ecommerce.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.userID')">userID</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="userID"
                    id="ecommerce-userID"
                    data-cy="userID"
                    v-model="ecommerceProcess.ecommerce.userID"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.productQuantity')">productQuantity</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="productQuantity"
                    id="ecommerce-productQuantity"
                    data-cy="productQuantity"
                    v-model="ecommerceProcess.ecommerce.productQuantity"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.confirmation')">confirmation</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="confirmation"
                    id="ecommerce-confirmation"
                    data-cy="confirmation"
                    v-model="ecommerceProcess.ecommerce.confirmation"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.invoiceCode')">invoiceCode</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="invoiceCode"
                    id="ecommerce-invoiceCode"
                    data-cy="invoiceCode"
                    v-model="ecommerceProcess.ecommerce.invoiceCode"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.invoicePrice')">invoicePrice</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="invoicePrice"
                    id="ecommerce-invoicePrice"
                    data-cy="invoicePrice"
                    v-model="ecommerceProcess.ecommerce.invoicePrice"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.saleDate')">saleDate</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="saleDate"
                    id="ecommerce-saleDate"
                    data-cy="saleDate"
                    v-model="ecommerceProcess.ecommerce.saleDate"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.productsAvailable')">productsAvailable</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="productsAvailable"
                    id="ecommerce-productsAvailable"
                    data-cy="productsAvailable"
                    v-model="ecommerceProcess.ecommerce.productsAvailable"
                  />
                </div>
              </div>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ecommerceApp.ecommerceProcess.status')">status</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="status"
                    id="ecommerce-status"
                    data-cy="status"
                    v-model="ecommerceProcess.ecommerce.status"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ecommerce-process-details.component.ts"></script>

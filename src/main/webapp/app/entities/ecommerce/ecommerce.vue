<template>
  <div>
    <h2 id="page-heading" data-cy="EcommerceHeading">
      <span v-text="$t('ecommerceApp.ecommerce.home.title')" id="ecommerce-heading">Ecommerces</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ecommerceApp.ecommerce.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && ecommerces && ecommerces.length === 0">
      <span v-text="$t('ecommerceApp.ecommerce.home.notFound')">No ecommerces found</span>
    </div>
    <div class="table-responsive" v-if="ecommerces && ecommerces.length > 0">
      <table class="table table-striped" aria-describedby="ecommerces">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.userID')">User ID</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.productQuantity')">Product Quantity</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.confirmation')">Confirmation</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.invoiceCode')">Invoice Code</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.invoicePrice')">Invoice Price</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.saleDate')">Sale Date</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.productsAvailable')">Products Available</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.status')">Status</span></th>
            <th scope="row"><span v-text="$t('ecommerceApp.ecommerce.produto')">Produto</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ecommerce in ecommerces" :key="ecommerce.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'EcommerceView', params: { ecommerceId: ecommerce.id } }">{{ ecommerce.id }}</router-link>
            </td>
            <td>{{ ecommerce.userID }}</td>
            <td>{{ ecommerce.productQuantity }}</td>
            <td>{{ ecommerce.confirmation }}</td>
            <td>{{ ecommerce.invoiceCode }}</td>
            <td>{{ ecommerce.invoicePrice }}</td>
            <td>{{ ecommerce.saleDate }}</td>
            <td>{{ ecommerce.productsAvailable }}</td>
            <td>{{ ecommerce.status }}</td>
            <td>
              <div v-if="ecommerce.produto">
                <router-link :to="{ name: 'ProdutoView', params: { produtoId: ecommerce.produto.id } }">{{
                  ecommerce.produto.name
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'EcommerceView', params: { ecommerceId: ecommerce.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="ecommerceApp.ecommerce.delete.question" data-cy="ecommerceDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-ecommerce-heading" v-text="$t('ecommerceApp.ecommerce.delete.question', { id: removeId })">
          Are you sure you want to delete this Ecommerce?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-ecommerce"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeEcommerce()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./ecommerce.component.ts"></script>

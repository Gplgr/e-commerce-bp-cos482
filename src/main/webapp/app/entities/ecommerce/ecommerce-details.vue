<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="ecommerce">
        <h2 class="jh-entity-heading" data-cy="ecommerceDetailsHeading">
          <span v-text="$t('ecommerceApp.ecommerce.detail.title')">Ecommerce</span> {{ ecommerce.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.userID')">User ID</span>
          </dt>
          <dd>
            <span>{{ ecommerce.userID }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.productQuantity')">Product Quantity</span>
          </dt>
          <dd>
            <span>{{ ecommerce.productQuantity }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.confirmation')">Confirmation</span>
          </dt>
          <dd>
            <span>{{ ecommerce.confirmation }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.invoiceCode')">Invoice Code</span>
          </dt>
          <dd>
            <span>{{ ecommerce.invoiceCode }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.invoicePrice')">Invoice Price</span>
          </dt>
          <dd>
            <span>{{ ecommerce.invoicePrice }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.saleDate')">Sale Date</span>
          </dt>
          <dd>
            <span>{{ ecommerce.saleDate }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.productsAvailable')">Products Available</span>
          </dt>
          <dd>
            <span>{{ ecommerce.productsAvailable }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.status')">Status</span>
          </dt>
          <dd>
            <span>{{ ecommerce.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.ecommerce.produto')">Produto</span>
          </dt>
          <dd>
            <div v-if="ecommerce.produto">
              <router-link :to="{ name: 'ProdutoView', params: { produtoId: ecommerce.produto.id } }">{{
                ecommerce.produto.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ecommerce-details.component.ts"></script>

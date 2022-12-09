<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="produto">
        <h2 class="jh-entity-heading" data-cy="produtoDetailsHeading">
          <span v-text="$t('ecommerceApp.produto.detail.title')">Produto</span> {{ produto.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('ecommerceApp.produto.name')">Name</span>
          </dt>
          <dd>
            <span>{{ produto.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('ecommerceApp.produto.price')">Price</span>
          </dt>
          <dd>
            <span>{{ produto.price }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="produto.id" :to="{ name: 'ProdutoEdit', params: { produtoId: produto.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./produto-details.component.ts"></script>

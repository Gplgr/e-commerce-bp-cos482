<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="ecommerceApp.produto.home.createOrEditLabel"
          data-cy="ProdutoCreateUpdateHeading"
          v-text="$t('ecommerceApp.produto.home.createOrEditLabel')"
        >
          Create or edit a Produto
        </h2>
        <div>
          <div class="form-group" v-if="produto.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="produto.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('ecommerceApp.produto.name')" for="produto-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="produto-name"
              data-cy="name"
              :class="{ valid: !$v.produto.name.$invalid, invalid: $v.produto.name.$invalid }"
              v-model="$v.produto.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('ecommerceApp.produto.price')" for="produto-price">Price</label>
            <input
              type="number"
              class="form-control"
              name="price"
              id="produto-price"
              data-cy="price"
              :class="{ valid: !$v.produto.price.$invalid, invalid: $v.produto.price.$invalid }"
              v-model.number="$v.produto.price.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.produto.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./produto-update.component.ts"></script>

<template>
  <div>
    <div>UserDetail.vue</div>
    <!-- start of breadcrumb -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-6 col-12">
            <div class="breadcrumbs-content">
              <h1 class="page-title">회원정보</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end of breadcrumb -->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  methods: {
    changePageUserUpdate() {
      this.$emit();
    },
  },
};
</script>

<style></style>

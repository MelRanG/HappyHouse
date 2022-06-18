<template>
  <div>
    <div>NoticeView.vue</div>
    <!-- start of breadcrumb -->
    <div class="breadcrumbs">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-6 col-md-6 col-12">
            <div class="breadcrumbs-content">
              <h1 class="page-title">공지사항</h1>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end of breadcrumb -->
    <div class="notice">
      <div class="container">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.notice {
  background-color: #f9f9f9;
  padding-bottom: 300px;
}
</style>

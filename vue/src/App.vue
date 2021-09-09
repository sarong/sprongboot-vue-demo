<template>
  <div>
<!--头部-->
    <Header/>
    <div style="display: flex">
<!--      侧边栏-->
      <Aside/>
<!--      内容区域-->
    <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>
import Header from './components/Header'
import Aside from './components/Aside'
import Content from './views/Home'

export default {
  name: 'LayOut',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    Content,
    Header,
    Aside
  }
}
</script>

<style>

</style>

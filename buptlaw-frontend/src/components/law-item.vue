<script setup lang="ts">
import { Law }           from "../types/law";
import { getFormatDate } from "../utils/utils";
import { onMounted }     from "vue";

const props = defineProps<{ law: Law }>()

let law: Law
onMounted(() => {
  law = props.law
})
</script>

<template>
  <el-row class="law-item">
    <el-col :span="1" style="display: flex; align-items: center">
      <div class="law-item-dot"/>
    </el-col>
    <el-col :span="17" style="cursor: pointer">
      <router-link :to="{ name: 'law-detail', params: { id: props.law.id } }"
                   style="text-decoration: none; color: var(--text-color)">
        {{ props.law.title }}
      </router-link>
    </el-col>
    <el-col :span="6" style="text-align: center; color: var(--theme-active-color)">
      {{ props.law.publish ? getFormatDate(props.law.publish) : '未知' }}
    </el-col>
  </el-row>
</template>

<script lang="ts">
export default {
  name: "law-item"
}
</script>

<style scoped>
.law-item {
  width: 100%;
  height: 42px;
  display: flex;
  align-items: center;
  border-bottom: 1px dashed var(--theme-color);
}

.law-item-dot {
  content: '';
  width: 4px;
  height: 4px;
  min-width: 4px;
  min-height: 4px;
  border-radius: 2px;
  margin-left: 8px;
  background: var(--theme-color);
}
</style>

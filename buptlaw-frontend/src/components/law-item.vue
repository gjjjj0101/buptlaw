<script setup lang="ts">
import { Law }           from "../types/law";
import { getFormatDate } from "../utils/utils";

const props = defineProps<{ law: Law }>()
</script>

<template>
  <el-row class="law-item">
    <el-col :span="1" style="display: flex; align-items: center">
      <div class="law-item-dot"/>
    </el-col>
    <el-col :span="17">
      {{ props.law.title }}
    </el-col>
    <el-col :span="6" style="text-align: center">
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

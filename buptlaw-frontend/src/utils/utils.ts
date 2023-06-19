//+-------------------------------------------------------------------------
//
//  工具函数
//
//  File:       utils.ts
//
//  Directory:  buptlaw-frontend/src/utils
//
//  History:    6月-19-2023   QQK  Created
//
//--------------------------------------------------------------------------

// 传入10位时间戳，返回格式化后的日期
// xxxx年xx月xx日
export const getFormatDate = (timestamp: number) => {
  const date = new Date(timestamp * 1000);
  return `${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}日`;
}

// 传入法律状态，返回对应的文字
export const getFormatLawStatus = (status: string) => {
  switch (status) {
    case '1':
      return '已生效';
    case '3':
      return '未生效';
    case '5':
      return '已修改';
    case '7':
      return '无时效';
    case '9':
      return '已废止';
    default:
      return '未知';
  }
}

// 传入法律状态，返回对应的颜色
export const getFormatLawStatusColor = (status: string) => {
  switch (status) {
    case '1':
      return '#67C23A';
    case '3':
      return '#409EFF';
    case '5':
      return '#E6A23C';
    case '7':
      return '#ce6cf5';
    case '9':
      return '#F56C6C';
    default:
      return '#909399';
  }
}

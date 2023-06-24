//+-------------------------------------------------------------------------
//
//  行为相关的常量
//
//  File:       action.ts
//
//  Directory:  buptlaw-frontend/src/consts
//
//  History:    6月-24-2023   QQK  Created
//
//--------------------------------------------------------------------------

export const LAW_ACTION_MAP = {
  '01': '浏览',
  '02': '收藏',
}

export const RECORD_ACTION_MAP = {
  '01': '创建',
  '02': '更新',
  '03': '删除',
  '04': '恢复',
}

// 法律行为枚举
export enum LawAction {
  BROWSE = '01',
  COLLECT = '02',
}

// 记录行为枚举
export enum RecordAction {
  CREATE = '01',
  UPDATE = '02',
  DELETE = '03',
  RECOVER = '04',
}

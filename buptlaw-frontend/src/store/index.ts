//+-------------------------------------------------------------------------
//
//  全局状态管理
//
//  File:       index.ts
//
//  Directory:  buptlaw-frontend/src/store
//
//  History:    6月-16-2023   QQK  Created
//
//--------------------------------------------------------------------------

import { createStore } from 'vuex';

export interface State {
  token: string | null,
  username: string | null,
}

export const store = createStore<State>({
  state: {
    token: localStorage.getItem('token'),
    username: localStorage.getItem('username'),
  },
  mutations: {
    setState(state, payload) {
      // 将 payload 的所有 State 接口的属性复制到 state
      Object.assign(state, payload);
    }
  }
})

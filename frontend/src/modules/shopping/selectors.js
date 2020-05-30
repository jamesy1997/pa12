const getModuleState = state => state.shopping;

export const getSession = state =>
    getModuleState(state).session;

export const getLastOrderId = state =>
    getModuleState(state).lastOrderId;

export const getOrderSearch = state =>
    getModuleState(state).orderSearch;

export const getOrder = state =>
    getModuleState(state).order;
    
const getModuleState = state => state.shopping;

export const getSession = state =>
    getModuleState(state).session;
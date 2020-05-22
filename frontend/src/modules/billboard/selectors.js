const getModuleState = state => state.billboard;

export const getMovies = state => 
    getModuleState(state).movies;

export const getBillboardDate = state =>
    getModuleState(state).billboardDate;

export const getSessions = state => 
    getModuleState(state).sessions;
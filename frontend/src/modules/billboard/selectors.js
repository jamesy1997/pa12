const getModuleState = state => state.billboard;

export const getMovies = state => 
    getModuleState(state).movies;

export const getBillboardDate = state =>
    getModuleState(state).billboardDate;

export const getSessions = state => 
    getModuleState(state).sessions;

export const getCities = state =>
    getModuleState(state).cities;

export const getCinemas = state =>
    getModuleState(state).cinemas;
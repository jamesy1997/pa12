import {init} from './appFetch';
import * as userService from './userService';
import * as billboardService from './billboardService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, billboardService};

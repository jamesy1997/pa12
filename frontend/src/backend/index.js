import {init} from './appFetch';
import * as userService from './userService';
import * as billboardService from './billboardService';
import * as shoppingService from './shoppingService';

export {default as NetworkError} from "./NetworkError";

export default {init, userService, billboardService, shoppingService};

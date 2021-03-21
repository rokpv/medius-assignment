import {SolutionStep} from './SolutionStep';
import {Problem} from './Problem';

export interface Solution {
  id: string;
  problem: Problem;
  steps: SolutionStep[];
}

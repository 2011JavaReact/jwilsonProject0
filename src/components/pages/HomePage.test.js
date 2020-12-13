import HomePage from "./Homepage";
import {shallow} from "enzyme";

describe("HomePage Component", () => {
    it("has title", () => {
        const wrapper = shallow(<HomePage/>);
        expect(wrapper.find("#showcase")).toBeTruthy();
    })
})
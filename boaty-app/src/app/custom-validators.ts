import {AbstractControl, FormControl} from "@angular/forms";

export class CustomValidators {

    static matchOtherValidator(otherControlName: string) {
        return function controlMatcher(control: FormControl) {
            if (!control.parent) {
                return null;
            }
            const otherControl = control.parent.get(otherControlName) as FormControl;
            if (!otherControl) {
                return null;
            }
            otherControl.valueChanges.subscribe(() => {
                control.updateValueAndValidity();
            });
            if (otherControl.value !== control.value) {
                return { matchOther: true };
            }
            return null;
        };
    }
}

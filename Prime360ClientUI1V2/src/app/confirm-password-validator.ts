import { FormGroup } from '@angular/forms';

export class ConfirmPasswordValidator {
    static validate(resetFormGroup: FormGroup) {
        let newPassword = resetFormGroup.controls.newPassword.value;
        let confirmPassword = resetFormGroup.controls.confirmPassword.value;

        if (confirmPassword === undefined || confirmPassword === '') {
            return null;
        }

        if (confirmPassword !== newPassword) {
            return {
                doesMatchPassword: true
            };
        }

        return null;

    }
    
}
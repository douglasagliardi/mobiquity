package com.mobiquityinc.validate;

import com.mobiquityinc.dto.PackageInputRequest;

public abstract class InputProcessor {
    private InputProcessor next;

    /**
     * Builds chains of input processor objects.
     */
    public InputProcessor linkWith(InputProcessor next) {
        this.next = next;
        return next;
    }

    /**
     * Subclasses will implement this method with concrete checks.
     */
    public abstract boolean check(PackageInputRequest request);

    /**
     * Runs check on the next object in chain or ends traversing if we're in
     * last object in chain.
     */
    protected boolean checkNext(PackageInputRequest request) {
        if (next == null) {
            return true;
        }
        return next.check(request);
    }
}

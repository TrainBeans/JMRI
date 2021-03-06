// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.beans;

import javax.annotation.Nonnull;

/**
 * An object that has a publicly mutable identity property.
 *
 * @author Randall Wood Copyright 2020
 * @see Identifiable
 */
public interface MutableIdentifiable extends Identifiable {

    /**
     * Set the identity.
     *
     * @param id the new identity
     */
    public abstract void setId(@Nonnull String id);
}

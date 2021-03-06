/**
 *
 * Copyright © 2014 Florian Schmaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jxmpp.jid.impl;

import org.jxmpp.jid.AbstractJid;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.stringprep.XmppStringPrepUtil;
import org.jxmpp.stringprep.XmppStringprepException;

public class DomainpartJid extends AbstractJid implements DomainBareJid {

	protected final String domain;

	DomainpartJid(String domain) throws XmppStringprepException {
		domain = XmppStringPrepUtil.nodeprep(domain);
		assertNotLongerThen1023BytesOrEmpty(domain);
		this.domain = domain;
	}

	public final String getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		return domain;
	}

	@Override
	public String asUnescapedString() {
		// No un-escaping necessary for DomainpartJid
		return toString();
	}

	@Override
	public final int compareTo(Jid  other) {
		String otherString = other.toString();
		String myString = toString();
		return myString.compareTo(otherString);
	}

	@Override
	public final int hashCode() {
		return toString().hashCode();
	}

	@Override
	public final boolean equals(Object other) {
		if (!(other instanceof DomainpartJid)) {
			return false;
		}
		DomainpartJid otherJid = (DomainpartJid) other;
		return hashCode() == otherJid.hashCode();
	}

	public static void assertNotLongerThen1023BytesOrEmpty(String string) {
		char[] bytes = string.toCharArray();
		if (bytes.length > 1023) {
			throw new IllegalArgumentException("Given string '" + string + "' is longer then 1023 bytes");
		} else if (bytes.length == 0) {
			throw new IllegalArgumentException("Argument can't be the empty string");
		}
	}

	@Override
	public DomainBareJid asDomainBareJid() {
		return this;
	}

	@Override
	public boolean hasNoResource() {
		return true;
	}
}

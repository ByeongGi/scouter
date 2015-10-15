/*
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License. 
 */

package scouter.lang.pack;

import java.io.IOException;

import scouter.io.DataInputX;
import scouter.io.DataOutputX;
import scouter.lang.value.MapValue;
import scouter.lang.value.Value;
import scouter.lang.value.ValueEnum;
import scouter.util.ArrayUtil;
import scouter.util.DateUtil;
import scouter.util.Hexa32;

public class SummaryPack implements Pack {

	public long time;
	public int objHash;
	public byte stype;
    public MapValue table = new MapValue();

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Summary ");
		sb.append(DateUtil.timestamp(time));
		sb.append(" objHash=").append(Hexa32.toString32(objHash));
		sb.append(" stype=").append(stype);
				sb.append(" " + table.keySet());
		
		return sb.toString();
	}

	public byte getPackType() {
		return PackEnum.SUMMARY;
	}

	public void write(DataOutputX o) throws IOException {
		o.writeDecimal(time);
		o.writeInt(objHash);
		o.writeByte(stype);
		
		o.writeValue(table);

	}

	public Pack read(DataInputX n) throws IOException {

		this.time = n.readDecimal();
		this.objHash = n.readInt();
		this.stype = n.readByte();

		this.table=(MapValue)n.readValue();
	

		return this;
	}

}
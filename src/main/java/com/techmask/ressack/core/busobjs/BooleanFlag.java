package com.techmask.ressack.core.busobjs;

import com.techmask.ressack.core.utils.StringUtils;

public enum BooleanFlag {

  Y,N;

  
  public static BooleanFlag getInstance(String flag) {
      if (StringUtils.isBlank(flag))
          return BooleanFlag.N;
      else if ("Y".equalsIgnoreCase(flag) ||
          "YES".equalsIgnoreCase(flag) ||
          "ON".equalsIgnoreCase(flag) ||
          "TRUE".equalsIgnoreCase(flag) ||
          "CHECKED".equalsIgnoreCase(flag) ||
          "SELECTED".equalsIgnoreCase(flag)) {
          return BooleanFlag.Y;
      } else
          return BooleanFlag.N;
  }

  /**
   * Return an instance of the YesNoFlag matching the given boolean,
   * where true returns Y and false returns N.
   */
  public static BooleanFlag getInstance(boolean flag) {
      return flag == true ? Y : N;
  }

  /**
   * Return a boolean representation of this YesNoFlag,
   * where Y returns true and N returns false.
   */
  public boolean booleanValue() {
      return this.name().equals(Y.name());
  }


}

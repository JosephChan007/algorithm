package org.joseph.algorithm.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node extends MistObject {

    public Integer value;
    public Node next;


}

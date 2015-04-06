package cn.wayne.mamypoko.base.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lumia on 2015/4/5.
 */
public interface EntityList extends Serializable {

    List<?> getList();
}

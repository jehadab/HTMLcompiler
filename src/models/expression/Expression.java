package models.expression;

import models.AbstractASTNode;
import models.util.Formatter;

public abstract class Expression extends AbstractASTNode {
    public abstract String getExpressionForJS();
}

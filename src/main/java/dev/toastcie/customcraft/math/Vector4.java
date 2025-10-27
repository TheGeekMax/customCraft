package dev.toastcie.customcraft.math;

public class Vector4<T> implements Comparable<Vector4<T>> {
    public T x;

    public T y;
    public T z;
    public T w;

    public Vector4(T x, T y, T z, T w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    @Override
    public int compareTo(Vector4<T> tVector4) {
        //compare x, then y, then z, then w
        int cmpX = ((Comparable<T>) this.x).compareTo(tVector4.x);
        if (cmpX != 0) return cmpX;
        int cmpY = ((Comparable<T>) this.y).compareTo(tVector4.y);
        if (cmpY != 0) return cmpY;
        int cmpZ = ((Comparable<T>) this.z).compareTo(tVector4.z);
        if (cmpZ != 0) return cmpZ;
        return ((Comparable<T>) this.w).compareTo(tVector4.w);
    }

    public <R> Vector4<R> map(Function4<T, R> func) {
        return new Vector4<>(
                func.apply(this.x),
                func.apply(this.y),
                func.apply(this.z),
                func.apply(this.w)
        );
    }

    public <R> R reduce(Function4<Vector4<T>, R> func) {
        return func.apply(this);
    }

    public interface Function4<T, R> {
        R apply(T t);
    }
}

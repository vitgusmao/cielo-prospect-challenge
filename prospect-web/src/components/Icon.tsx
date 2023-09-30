import React from 'react';
import styled from 'styled-components';

const StyledSpan = styled.span<{ $height: string; $width: string }>`
  display: inline-flex;
  align-items: center;
  color: inherit;
  font-style: normal;
  line-height: 0;
  text-align: center;
  text-transform: none;
  vertical-align: -0.125em;
  text-rendering: optimizeLegibility;
  -webkit-font-smoothing: antialiased;

  svg {
    height: ${(p) => p.$height};
    width: ${(p) => p.$width};
  }
`;

export interface IconBaseProps extends React.HTMLProps<HTMLSpanElement> {
  spin?: boolean;
  rotate?: number;
}

export interface CustomIconComponentProps {
  fill: string;
  viewBox?: string;
  className?: string;
  style?: React.CSSProperties;
}
export interface IconComponentProps extends IconBaseProps {
  viewBox?: string;
  title?: string;
  iconProps?: Partial<CustomIconComponentProps>;
  width?: string;
  height?: string;
  component?:
    | React.ComponentType<
        CustomIconComponentProps | React.SVGProps<SVGSVGElement>
      >
    | React.ForwardRefExoticComponent<CustomIconComponentProps>;
  ariaLabel?: React.AriaAttributes['aria-label'];
}

const svgBaseProps = {
  stroke: 'currentcolor',
  fill: 'currentcolor',
  strokeWidth: 0,
};

const Icon: React.ForwardRefExoticComponent<
  IconComponentProps & React.RefAttributes<HTMLSpanElement>
> = React.forwardRef(
  (
    {
      component: Component,
      viewBox,
      spin,
      rotate,
      className,
      tabIndex,
      iconProps = {},
      width,
      height,
      'aria-label': ariaLabel,
    },
    ref
  ) => {
    const { className: svgClassName } = iconProps;
    const svgClassString = getSvgClassString(spin ?? false, svgClassName);
    const svgStyle = rotate
      ? {
          msTransform: `rotate(${rotate}deg)`,
          transform: `rotate(${rotate}deg)`,
        }
      : undefined;

    const innerSvgProps: CustomIconComponentProps = {
      ...svgBaseProps,
      ...iconProps,
      className: svgClassString,
      style: svgStyle,
      viewBox,
    };
    if (!viewBox) {
      delete innerSvgProps.viewBox;
    }

    let iconTabIndex = tabIndex;
    if (iconTabIndex === undefined) {
      iconTabIndex = -1;
    }

    const sizeProps = getSize(width, height);
    return Component ? (
      <StyledSpan
        role="img"
        aria-label={ariaLabel}
        ref={ref}
        tabIndex={iconTabIndex}
        className={className}
        $width={sizeProps.width}
        $height={sizeProps.height}
      >
        {/* eslint-disable-next-line react/jsx-props-no-spreading */}
        <Component {...innerSvgProps} />
      </StyledSpan>
    ) : null;
  }
);

function getSize(
  width: string | undefined,
  height: string | undefined
): { width: string; height: string } {
  if (!height && !width) {
    return { width: '1em', height: '1em' };
  }
  if (!width && height) {
    return { width: 'fit-content', height };
  }
  if (width && !height) {
    return { width, height: 'fit-content' };
  }

  if (!height || !width) {
    throw new Error('how could this even happen??');
  }

  return { width, height };
}

function getSvgClassString(spin: boolean, svgClassName: string | undefined) {
  const className = svgClassName || '';
  return spin ? className.concat('-spin') : className;
}

export default Icon;
